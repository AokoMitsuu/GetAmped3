	� G %/accs/floating_arms/floating_arms.mot o�       �V�n�6>;h�A�\�.W���n�f���"	�ɠeJfM��HoҦ���E�EO=���G�c��萴d�q6{H�@����f8����kI�R8B�8ì ������\7�9�!c��noѬॴf\�:,���`ݜ�m2'�`m��)�2��	a5p%��{e�rV9������}|�����ފy�\�0���㰲�7�w����"u=אw�N,�RJ�s���b<��H��=�,�Y��x��o��cF�3���=Ys�1Ȫ�k.j�4�U7	/m����p}�R�ǋhg�A����|1�~ɐ��k0M���;�iFl?��!?�/��t�����=��-�V;զ �U;�ۀ�)\��=�:���N�AM��V���1�I�b�V�=7��f+��v����K�k�aAJ���c!FiI���ݹ�
.l�_g`�C�='ᡀ�Q�;XtT�?�&��n��b:��9�<�H��yN�ɇں,��v�:�G�pꦌ_��3,����M&FөT�0L�z��r.�R�xf��hE�Näӄ���@����8S8�$���*Z%FL	c��q,i�������ĀȅRК�^m��ӏS*E\Dm���lW6���CAI>�1R%�M��Ix�!�xJ�Y��X��'	d��ֶ<���
~:��9���~���BP���M�

��T[>�9cH_�|���]�A���7糤��'��S�f�E�ޓ�?9�y��ZJ��/h��	Q�I"�Lӈ��§��}]O�c���M�����yUM�=�:a��I"Z����jt�T�*����d<��uG��E5��e\��?1��&�|	�q�T�S�v�U}��H�
g��t_�ц��5�n}�HRu�u`P���֔/"���覸���l�Tb�
l��e̮���ƺ�"n����ߍΥ ��a��Ԍy���8n��p�	W]�=�Ԝ�2�YT��5�4	(2�\�\6V�/�)�����$a6MT}��>��Y�1b0:7�S� �L�v��9�\��xd���m�;�)��PW�7�⒃Z�@��_���\�0:|=<;>�<:|}vr"�$��Ah�b�����ꌄ��������i$�   x  Lga2/stage/AmpedScript; e Lkotori/gfx/KGLScriptModel; beamL beamR start broken lockItemScript (ZZ)V
 f l m 
setVisible (Z)V
 f o p
 H 0 3 (ZI)V
 d o s setShadowState
 d u 3 setEdge
 f w p 	passAnime (FFI)V
 d y zD�  D��  	playSound
 d ~ 3D�� D�  D�  clearGenerate
 d � 1 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � setGenerate 8(Lkotori/gfx/KGLScriptModel;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/laser/l_bbss_gre2.scm!            >�  >�  >�               A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/gun/flush_doublegun.scm!            >L��>L��>���             	start_greD /efx/flash/glow.scm!        >L��?���?���?���             	lightgre2����D /efx/gun/shell_floating_x.scm�            @   @   @               � hit_bb  d�   �    N 6       ,�>�         	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � �-?��@   ����� kotori.geom.Quaternion?<)B?,j��l�=Y attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            D�@ -?��@   �����?J��?v��b��=-��-            -?��@   ������?QO�?�˽L�;=l�-            D�p -?��@   �L����?W��?	\6�4��<�\-            -?��@       ��?]}�>�����{<��>-            -?��@   >L����?b�>�Er����<�,-            D�� -?��@   >�����?g�o>�Nq��Y�<5@�-            -?��@   >�����?lz%>��r��P;��d-            -?��@   ?   ��?p�Q>�o��Y;Ø9-            -?��@   ?����?r��>�ol����;�u�-             lockTimeCount
 f � p getGenerate (I)Lkotori/gfx/KGLScriptModel;
 d � � g startScript (Ljava/lang/String;)Z
 � � � end kill
 � � 1D /accs/floating_arms/arms.scm�            ?�  ?�  ?�                getParentPlayerModel ()Lga2/stage/PlayerModel;
 f � � Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � � back2_R putParam '(Ljava/lang/String;Ljava/lang/Object;)V
 � � � bc_attacker setFlagsToModifier (ILjava/lang/String;I)V
 f � � floating_arms   
 f � 1 
stopScript
 f � 1 f h g i g j 1 6*� nW*� qW*2� rW*� nW*� qW<� *� tW�W���*� vW*� xW*|} �� {W*e� W* �� rW*}� �� {W* ȶ rW*��d� {W* ȶ rW*�� �� {W* ϶ W*� �W**�� �� �W**��� �� �W**��� �� �W*2� rW�M*��� �W*�,� �W*� �W*d� rW*�� ȶ {W*��� �W*�,� �W*� �W*d� rW*��� �W*�,� �W*� �W*d� rW*��,� {W*��� �W*�,� �W*� �W*d� rW*��� �W*�,� �W*� �W*d� rW*��� �W*�,� �W*� �W*d� rW*��,� {W*��� �W*�,� �W*� �W*d� rW*��� �W*�,� �W*� �W*d� rW*��� �W*�,� �W*� �W*d� rW<� '*��� �W*�,� �W*� �W*d� rW�W��ܱ  k 1  �*� nW*� �W*� rW*� qW*� �L*� �M*� �N+� 
+ö �W,� ,� �W-� -� �W�:*� �:� &� �W*϶ �:� �� �W*�� rW*1�ض �W*�� rW*� �W*� �W� ��,�    ?S(6���p?�L^?>t�?�[�  