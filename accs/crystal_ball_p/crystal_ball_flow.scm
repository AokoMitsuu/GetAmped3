	� G %/accs/crystal_ball_p/crystal_ball.mot��       �S]o�0}�`������M�k�hݤ���9`�W��q]��5$mV�Z�>���s?�����Ӭ����hZ0|R�,��*^�ضyYK��k���8�����Z0Y�A�n��O���SY����.�����7�+���q�;�R��;%��9؁�wk[�u����,�
1-��V)3�~^��6[|}�qvz�=8�Z�;A]RmH'ȋ��>L��=v�R�=:sZ�.b��gN��\H�^��Z��S� '��q~���y�2��:����a��bX�s!�T�9!����`�h��j404��Z��X�|k�1����2G^�Li�=�+��cC���A�M"#��q��#,H�bʄ>�Ͼ�X-g��5R��*(�\�p���E�֩7)�^ܛ��X�G\V�4��6�G�h�/RuC$�CbQ�[������'^�!�`�X��-o�R�D���������֮iF�UDӴ	6�c쿏rNK�����3�/�F��`(��a �2�-��ݡ9�Y���G7�2�!���� �>OH<*��l��r�P�g�s�_%Sb�VJ�ȘZ�Ϫ��M� �5��[~tK?�'O3�_�K��,G������z2������S�T-XHv����u������� Q   N  Lga2/stage/AmpedScript; e cnt Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� lastmat MAT ()Lkotori/geom/Matrix;
 H l m mat tmp v ()Lkotori/geom/Vector3D;
 H  r i j Lkotori/data/KGLGenerationData; smokeD "/efx/smoke/smoke_crystalball_p.ptc�    ����    >���>���>���C��        A (F)V start 	 { S  Lkotori/scp/KotoriScript; } realtime
 ~  2 cos (F)F
 H � �@H��;o	 { R  sin
 H � �:ě� getGlobalMatrix *(Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
 d � �  	translate ,(Lkotori/geom/Vector3D;)Lkotori/geom/Matrix;
 � � � interpolate S(Lkotori/geom/Matrix;Lkotori/geom/Matrix;FLkotori/geom/Matrix;)Lkotori/geom/Matrix;
 � � �
 � + � invert
 � � m mult34 >(Lkotori/geom/Matrix;Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
 � � � setExtraMatrix (Lkotori/geom/Matrix;)V
 d � �
 H 0 3 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D (accs/crystal_ball_p/crystal_ball_out.scm�            ?�  ?�  ?�              AD (accs/crystal_ball_p/crystal_ball_mix.scm�            ?�  ?�  ?�              A setEdge (Z)V
 f � �
 H 0 1<� 
accumlimit
 ~ � 3 rndf
 H � � v hpb (FFF)V
 � � �C�  D efx/flash/glow01_purple10.ptc�            ?fff?fff?fff            A f	 g  i h k  o  p  q  t  u  w v - 1  j�� n�� n�� n�� s�x��  Q y  c��*� ���jb� �bj� |��*� ���jb� �� �*�� �W��� �W��#�� �W��� �W�� �W���� �W*�� �W�  z 1  �*� �W*�� �W*�� �W*� �W�*� �W*�� �W� u�2�  *�� W* ȶ �W*� �W�Y`�W�2� J*�� W* ȶ �W��� �� �W*�� �W��� �� �W*�� �W*�� �W*� �W����� \�h:            >8Q�>8Q�>8Q��  