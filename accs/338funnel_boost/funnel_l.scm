	� G %/accs/338funnel_boost/funnel_pose.mot��       �UM��F>V^i��hQ�x���WU��hUQ�E\�י�Z�x�q�fɡE�8�P	����D%�\�K*"�Կ���'�.��f�dޏ�}���'�S��"�dV:Y��f^��wi�z������*5�^iUfႩ<<E�$�K]u4_�M��q�߃�evJ�<�dF��|H��VʺҖ=0�(�P`:�0Zʌ(���u�y�v�K��I����R2A�|���y�)̤fe�D�E��� � ނ�;B%��S�h��	�KVj�=�У1u�k{�2~�R���"�DΏڌ+�
��0=O����6+��ad�Qi
H��`0��i�9��-~��P}�?��
�M �}L�+G�X�6�(������.kj�])3%�	J������Py��`�$s%Y;�	��l[��U�t�T�I��ERU��y��r��8����V?��O$U�D�q#I�ӝ�BO�RqU���x�z�]Ʃ9^�1�|��&��M^�>	�UƮ�"�����H�Y`c|����0��1��`����0��W��X.�>�o ��fA��H�vr%����s�X��q&V�OvEm�C~�+�O8Zy?���,�Lv-��t�{m�Շ@���>��b����B���쒥����	[���bOŵ1<\���iG�����`�8�.)�-F���hب�JAŧc6���l(��o5d<o7+N�PHZ��gx����k�]���ywuNn�=�-�8G�e@NZRVb�ȥ;���Q��U4}n��&�ґ;�1�:���v\�+8�8i��	��@A��6�3���M��A��N�u]�E��/�?��<'Y��3��L��,��0�u$���U��<��0
��p n��r�RӞWm���2n�w�R.�l�Eф�ͳ����_o^�ݼ|�����_��ϻ���\�U��7�   l  Lga2/stage/AmpedScript; e smat MAT ()Lkotori/geom/Matrix;
 H h i emat ext distance tmp2   len-             next@@   v-             Lkotori/geom/Quaternion; q�� kotori.geom.Quaternion?�               done 
movefunnel (F)V 
stopfunnel start  interpolate S(Lkotori/geom/Matrix;Lkotori/geom/Matrix;FLkotori/geom/Matrix;)Lkotori/geom/Matrix;
 ~  � getGlobalMatrix *(Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
 d � � invert
 ~ � i mult34
 ~ � �
 ~ � � ([FI)F
 f * �  (FFF)Lkotori/geom/Vector3D;
 � + � e [F	 ~ � � sub
 � � � length ()F
 � � �
 ~ - i setExtraMatrix (Lkotori/geom/Matrix;)V
 d � �
 H 0 3 anime (FFFZ)V
 d � �A   @�  
 ~ + � Lkotori/geom/KMath; � clamp (III)I
 � � �C�  <u	 � Q  cos (F)F
 H � �@I�>L��=���	 � R  sin
 H � �>�  >���	 � S  	translate ,(Lkotori/geom/Vector3D;)Lkotori/geom/Matrix;
 ~ � �B    getHPB .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 ~ � � v getHPBQuaternion 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �?�� 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D &/accs/338funnel_boost/funnel_gene2.scm             ?�  ?�  ?�  B�      A     
after_turn Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 rndc
 H � � f
 g  k  l  m  n  p  r  t  w v y  - 1  $� j� � j�� j�o�q�s�u�x��  z {  !� �#�� �W*�� �W�� �W��� �W�  | 1  u*�� �W��� �W��� �W�*�� �� �*�� �� �*�� �� �� �W��� �W�*�� �� �*�� �� �*�� �� �� �W�� ���  } 1 ��� �W*�� �W*� �W*��� �W*
� �W<=*�� �W��,�t���� �	�gd,�^����	*�� �W� �� ��� �W�	� �d<�Й 
,� WdZ,� ��F�%f�jF���jbj� ��j�f� ����j�j� ��j�b� ����j�j� ��j��j�j� ��jb%b�b� ���� �W6� �`��j�n� �8*fn� Wp� c��� �W�� ��f�� ��� ��� �W�*�� �� �*�� �� �*�� �� �� �W*��� �W*�ɶ �W*� �W* ȶ �W*� �W�W(��e=�	� sb� �b� W�*� W* ȶ �W*� �W�W��p� ��  =|  �.  >P  >�� >s� �  